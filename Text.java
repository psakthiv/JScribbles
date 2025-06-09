import React from 'react';
import { ShieldCheck, Bell, Settings } from 'lucide-react';

export default function SavingsDashboard() {
  return (
    <div className="min-h-screen bg-gray-50 font-sans">
      {/* Header */}
      <header className="bg-[#003366] text-white px-6 py-4 flex items-center justify-between shadow-md">
        <div className="flex items-center space-x-3">
          <ShieldCheck size={28} className="text-white" />
          <div className="text-2xl font-bold tracking-wide">JPMC</div>
        </div>
        <nav className="space-x-8 text-sm font-medium">
          <a href="#" className="border-b-2 border-white pb-1">Accounts</a>
          <a href="#" className="relative">
            Alerts
            <span className="bg-blue-500 text-white text-xs px-2 py-0.5 rounded-full absolute -top-3 -right-4">1</span>
          </a>
          <a href="#">Tools</a>
          <a href="#">More</a>
        </nav>
      </header>

      {/* Content */}
      <main className="p-6 max-w-4xl mx-auto">
        <h1 className="text-3xl font-semibold text-[#003366] mb-8">Set Savings Rules</h1>

        {/* Streak Meter Section */}
        <div className="bg-white p-6 rounded-xl shadow-md text-center mb-8">
          <div className="text-sm text-green-600 font-semibold">+2 DAYS</div>
          <div className="text-5xl font-bold text-[#003366] mb-2">5-day streak!</div>
          <a href="#" className="text-blue-600 underline text-sm">View progress &gt;</a>

          <div className="mt-6">
            <div className="w-full h-3 bg-gradient-to-r from-red-500 via-yellow-400 to-green-500 rounded-full relative">
              <div className="absolute top-[-10px] left-[80%] text-lg">⚪</div>
            </div>
          </div>
        </div>

        {/* Welcome Section */}
        <div className="bg-blue-100 p-5 rounded-lg shadow-sm flex items-center space-x-4 mb-6">
          <div className="text-4xl">🎉</div>
          <div className="text-left">
            <div className="text-lg font-semibold text-[#003366]">WELCOME BACK</div>
            <p className="text-gray-700">You’ve stashed <span className="font-bold">$4.67+</span> more funds!<br />Keep it up!</p>
          </div>
        </div>

        {/* Alert Section */}
        <div className="bg-white border border-blue-200 p-4 rounded-md flex justify-between items-center shadow-sm mb-6">
          <div className="flex items-center gap-2 text-gray-700">
            <Bell className="text-blue-600" size={18} />
            <span>You have <strong>1 unread alert</strong>.</span>
          </div>
          <a href="#" className="text-blue-600 font-medium text-sm">See details &gt;</a>
        </div>

        {/* Contributions Section */}
        <div className="mt-4">
          <h2 className="text-xl font-semibold text-[#003366] mb-4">Top bars that contribute to savings</h2>
          <div className="grid grid-cols-2 md:grid-cols-3 gap-4 text-gray-800">
            <div className="p-3 border border-gray-200 rounded-lg bg-white shadow-sm">The Local Tavern</div>
            <div className="p-3 border border-gray-200 rounded-lg bg-white shadow-sm">City Grocers</div>
            <div className="p-3 border border-gray-200 rounded-lg bg-white shadow-sm">Joe's Bar & Grill</div>
          </div>
        </div>
      </main>
    </div>
  );
}
