Take the US, for instance. The personal savings rate as of this year is just 4.6%. That’s… not great and is well beneath the historical average of around 8.4%.

And while teens and Gen Z talk a good game—87% of teens say saving is a priority—only around 42% actually have money set aside.

Now here's the interesting part: a recent Ipsos report says about 83% of teens are saving money in some form. Sounds promising, right? But—more than half of them have less than $500 saved, and about 17% aren’t saving at all.

Why is that? A big reason is impulse buying. Whether it’s a new sneaker drop, a viral product on Instagram, or a flash sale notification—today’s teens are growing up in a world of “buy now, think later.”

Add to that the magic of one-click payments, BNPL apps, and instant gratification from online shopping—saving money just can’t compete with the thrill of spending.

So even though the intention to save is real, it gets buried under constant temptation. This is where we, as a bank, can step in.












Okay, so hear me out…

Did you know that in countries like the US and UK, people really do want to save—but just can’t seem to get around to it?

Take the US, for instance. The personal savings rate as of this year is just 4.6%. That’s… not great and is well beneath the historical average of ~8.4 %. And while teens and Gen Z talk a good game—87% of teens say saving is a priority—only around 42% actually have money set aside.

Now here's the interesting part: a recent Ipsos report says about 83% of teens are saving money in some form. Sounds promising, right? But—more than half of them have less than $500 saved, and about 17% aren’t saving at all.

Why is that? A big reason is impulse buying. Whether it’s a new sneaker drop, a viral product on Instagram, or a flash sale notification—today’s teens are growing up in a world of “buy now, think later.”

Add to that the magic of one-click payments, BNPL apps, and instant gratification from online shopping—saving money just can’t compete with the thrill of spending.

So even though the intention to save is real, it gets buried under constant temptation. This is where we, as a bank, can step in.

So here’s the paradox: there’s interest in saving, but also a lot of inertia. It’s not that people don’t care—it’s just that traditional saving feels boring, slow, or like something future-you will figure out later.

[Problem – Why It Matters]

That’s a problem. Because without consistent saving habits, we’re looking at a financially anxious generation—one paycheck, one emergency away from chaos.

And for banks, especially one with the scale and trust of Chase, this is a wake-up call and an opportunity.

[Solution – Enter Gamification]

What if saving money was less like eating your vegetables and more like… playing a game or watching a reel?

Gamified savings is how we take that intention—and turn it into action.

At Chase, we're already building toward this:

Challenges that reward users for hitting their savings goals.

Auto-save rules that make money move quietly and consistently in the background.

And soon—imagine a world where users unlock badges, hit streaks, or even win prizes just for saving smart.

Think Duolingo, but instead of learning Spanish, you’re learning to stack your savings. ¡Muy bien!

[What Others Are Doing – Industry Buzz]

We’re not the only ones onto this. Apps like Yotta and PrizePool have turned savings into mini-lotteries. GoHenry teaches kids to save with quests and rewards. Even nonprofits like SaverLife are using gamification to help low-income families save for the first time.

So the playbook exists. But Chase can own this space—by blending smart design, behavioral science, and financial literacy.

[The Ask – What Chase Can Do Next]

So what can we do?

Build a youth-first savings experience with levels, goals, and social sharing.

Make winning money as exciting as spending it—prize-linked savings, anyone?

Celebrate good habits like streaks and milestones with little wins and nudges.

Make financial education snackable—think memes with money tips.

[Closing – The Vision]

Because at the end of the day, people don’t need another savings account.
They need an experience that makes saving irresistible.

Let’s give our customers that feeling of winning—even when they’re not spending.

Let’s gamify savings—and let’s help people Chase their goals, one streak at a time.

Thank you!












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
