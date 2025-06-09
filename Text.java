import React from 'react';

export default function SavingsDashboard() {
  return (
    <div className="min-h-screen bg-white font-sans">
      {/* Header */}
      <header className="bg-[#003366] text-white px-6 py-4 flex items-center justify-between">
        <div className="flex items-center space-x-2">
          <div className="text-2xl font-bold">🔷 JPMC</div>
        </div>
        <nav className="space-x-6 text-sm">
          <a href="#" className="border-b-2 border-white pb-1">Accounts</a>
          <a href="#" className="relative">
            Alerts <span className="bg-blue-500 text-white text-xs px-2 py-0.5 rounded-full absolute -top-2 -right-4">1</span>
          </a>
          <a href="#">Tools</a>
          <a href="#">...</a>
        </nav>
      </header>

      {/* Content */}
      <main className="p-6">
        <h1 className="text-3xl font-semibold text-[#003366] mb-6">Set Savings Rules</h1>

        {/* Streak meter */}
        <div className="bg-gray-50 p-6 rounded-xl shadow-md text-center mb-6">
          <div className="text-lg text-green-600 font-semibold">+2 DAYS</div>
          <div className="text-4xl font-bold text-[#003366] mb-2">5-day streak!</div>
          <a href="#" className="text-blue-600 underline text-sm">View progress &gt;</a>

          {/* Simulated meter */}
          <div className="mt-6">
            <div className="w-full h-3 bg-gradient-to-r from-red-500 via-yellow-400 to-green-500 rounded-full relative">
              <div className="absolute top-[-10px] right-[10%] text-sm">⚪</div>
            </div>
          </div>
        </div>

        {/* Welcome Back Section */}
        <div className="bg-blue-50 p-4 rounded-lg shadow-sm flex items-center space-x-4 mb-6">
          <div className="text-4xl">🎉</div>
          <div>
            <div className="font-semibold">WELCOME BACK</div>
            <div>You’ve stashed <span className="font-bold">$4.67+</span> more funds!<br />Keep it up!</div>
          </div>
        </div>

        {/* Alert box */}
        <div className="bg-white border border-blue-200 p-4 rounded-md flex justify-between items-center mb-6">
          <div>🔔 You have <strong>1 unread alert</strong>.</div>
          <a href="#" className="text-blue-600 font-medium">See details &gt;</a>
        </div>

        {/* Contributions section */}
        <div className="mt-4">
          <h2 className="text-xl font-semibold mb-2">Top bars that contribute to savings</h2>
          <div className="grid grid-cols-2 gap-4 text-gray-700">
            <div className="p-3 border rounded-lg">The Local Tavern</div>
            <div className="p-3 border rounded-lg">City Grocers</div>
          </div>
        </div>
      </main>
    </div>
  );
}
