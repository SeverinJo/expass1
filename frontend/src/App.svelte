<script>
    import { api } from './lib/api.js';

    // “identify” (basic)
    let userId = Number(localStorage.getItem('userId') || 0);
    let username = ''; let email = '';

    // create poll
    let question = '';
    let options = ['']; // array of option captions
    // list
    let created = [];
    let error = '';

    async function ensurePolls() {
        try { created = await api.listPolls(); }
        catch (e) { error = e.message; }
    }

    async function createUser() {
        error = '';
        try {
            const u = await api.createUser({ username, email });
            userId = u.id;
            localStorage.setItem('userId', String(userId));
            username = ''; email = '';
        } catch (e) { error = e.message; }
    }

    function addOptionRow() { options = [...options, '']; }
    function removeOptionRow(i) { options = options.filter((_, idx) => idx !== i); }

    async function createPoll() {
        error = '';
        try {
            if (!userId) throw new Error('Create a user first');
            if (!question.trim()) return;
            const now = new Date(), tomorrow = new Date(Date.now() + 86400000);
            const poll = await api.createPoll(userId, {
                question,
                publishedAt: now.toISOString(),
                validUntil: tomorrow.toISOString()
            });
            for (let i = 0; i < options.length; i++) {
                const caption = options[i].trim();
                if (caption) await api.addOption(poll.id, { caption, presentationOrder: i + 1 });
            }
            question = ''; options = [''];
            await ensurePolls();
        } catch (e) { error = e.message; }
    }

    async function vote(optionId) {
        if (!optionId) return; // bail early
        await api.castVote(userId, optionId);
        await ensurePolls();
    }

    ensurePolls();
</script>

<main>
    <h1>PollApp</h1>
    {#if error}<p style="color:#b00020">{error}</p>{/if}

    {#if !userId}
        <h3>Identify</h3>
        <input placeholder="Name" bind:value={username} />
        <input placeholder="Email" bind:value={email} />
        <button on:click={createUser}>Create user</button>
    {:else}
        <p>Signed in as user #{userId}</p>
    {/if}

    <h3>Create poll</h3>
    <input placeholder="Question" bind:value={question} />
    <div>
        {#each options as opt, i}
            <div style="display:flex; gap:.5rem; margin:.25rem 0;">
                <input placeholder={`Option ${i+1}`} bind:value={options[i]} />
                <button on:click={() => removeOptionRow(i)}>−</button>
            </div>
        {/each}
        <button on:click={addOptionRow}>+ Option</button>
    </div>
    <button on:click={createPoll}>Create</button>

    <h3>Polls</h3>
    {#if created.length === 0}
        <p>No created yet</p>
    {:else}
        <ul>
            {#each created as p (p.id)}
                {#if p && p.question}
                    <li style="margin:.5rem 0;">
                        <strong>{p.question}</strong>
                        {#if p.options && p.options.length}
                            <div>
                                {#each p.options as o (o.id)}
                                    <button
                                            disabled={!o?.id}
                                            on:click={() => vote(o.id)}>
                                        {o.caption}
                                    </button>
                                {/each}
                            </div>
                        {:else}
                            <em>No options yet</em>
                        {/if}
                    </li>
                {/if}
            {/each}
        </ul>
    {/if}
</main>
