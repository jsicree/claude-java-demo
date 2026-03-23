import { useState } from 'react';
import ProductsTab from './components/ProductsTab.jsx';
import CustomersTab from './components/CustomersTab.jsx';

const TABS = ['Products', 'Customers'];

export default function App() {
  const [activeTab, setActiveTab] = useState('Products');

  return (
    <div className="app">
      <header className="app-header">
        <h1>Claude Java Demo</h1>
      </header>
      <nav className="tab-nav">
        {TABS.map((tab) => (
          <button
            key={tab}
            className={`tab-btn${activeTab === tab ? ' active' : ''}`}
            onClick={() => setActiveTab(tab)}
          >
            {tab}
          </button>
        ))}
      </nav>
      <main className="app-main">
        {activeTab === 'Products' && <ProductsTab />}
        {activeTab === 'Customers' && <CustomersTab />}
      </main>
    </div>
  );
}
