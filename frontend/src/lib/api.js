//const baseUrl = import.meta.env.VITE_API_BASE_URL || 'http://127.0.0.1:8080';

const baseUrl = '';

async function req(path, init = {}) {
    const res = await fetch(path, {
        headers: { 'Content-Type': 'application/json', ...(init.headers || {}) },
        ...init
    });
    if (res.status === 204) return null;
    if (!res.ok) throw new Error(`${res.status} ${res.statusText}`);
    return res.json();
}

export const api = {
    // users
    createUser: (u) => req('/users', { method: 'POST', body: JSON.stringify(u) }),
    // polls
    listPolls: () => req('/polls'),
    createPoll: (creatorId, p) =>
        req(`/polls?creatorId=${creatorId}`, { method: 'POST', body: JSON.stringify(p) }),
    addOption: (pollId, o) =>
        req(`/polls/${pollId}/options`, { method: 'POST', body: JSON.stringify(o) }),
    // votes
    castVote: (userId, optionId) =>
        req(`/votes?userId=${userId}&optionId=${optionId}`, {
            method: 'POST', body: JSON.stringify({ publishedAt: new Date().toISOString() })
        }),
};
