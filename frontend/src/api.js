const BASE = '/api';

async function request(path, options = {}) {
  const res = await fetch(`${BASE}${path}`, {
    headers: { 'Content-Type': 'application/json' },
    ...options,
  });
  if (!res.ok) {
    const body = await res.json().catch(() => ({}));
    throw new Error(body.error || `HTTP ${res.status}`);
  }
  if (res.status === 204) return null;
  return res.json();
}

export const getProducts = () => request('/products');
export const createProduct = (name, price) =>
  request('/products', { method: 'POST', body: JSON.stringify({ name, price: parseFloat(price) }) });
export const deleteProduct = (id) => request(`/products/${id}`, { method: 'DELETE' });

export const getCustomers = () => request('/customers');
export const createCustomer = (name, email) =>
  request('/customers', { method: 'POST', body: JSON.stringify({ name, email }) });
export const deleteCustomer = (id) => request(`/customers/${id}`, { method: 'DELETE' });
