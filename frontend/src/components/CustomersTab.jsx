import { useState, useEffect, useCallback } from 'react';
import { getCustomers, createCustomer, deleteCustomer } from '../api.js';

export default function CustomersTab() {
  const [customers, setCustomers] = useState([]);
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const load = useCallback(async () => {
    try {
      setCustomers(await getCustomers());
    } catch (e) {
      setError(e.message);
    }
  }, []);

  useEffect(() => { load(); }, [load]);

  async function handleAdd(e) {
    e.preventDefault();
    setError('');
    if (!name.trim() || !email.trim()) return;
    setLoading(true);
    try {
      await createCustomer(name.trim(), email.trim());
      setName('');
      setEmail('');
      await load();
    } catch (e) {
      setError(e.message);
    } finally {
      setLoading(false);
    }
  }

  async function handleDelete(id) {
    if (!window.confirm('Delete this customer?')) return;
    setError('');
    try {
      await deleteCustomer(id);
      await load();
    } catch (e) {
      setError(e.message);
    }
  }

  return (
    <section>
      <h2>Customers</h2>
      {error && <div className="alert error">{error}</div>}

      <form className="add-form" onSubmit={handleAdd}>
        <input
          type="text"
          placeholder="Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <button type="submit" disabled={loading}>
          {loading ? 'Adding…' : 'Add Customer'}
        </button>
      </form>

      <table className="data-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {customers.length === 0 ? (
            <tr>
              <td colSpan={3} className="empty">No customers yet.</td>
            </tr>
          ) : (
            customers.map((c) => (
              <tr key={c.id}>
                <td>{c.name}</td>
                <td>{c.email}</td>
                <td>
                  <button className="btn-delete" onClick={() => handleDelete(c.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </section>
  );
}
