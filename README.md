# 💧 Jal Sanchay — Rainwater Harvesting Tracker

> *Jal Sanchay (जल संचय) means Water Conservation in Hindi*

A simple and powerful Android app that helps households
track, quantify, and understand the real-world value of
their rainwater harvesting efforts.

---

## 📱 About the App

Most households with rainwater harvesting systems never
know:
- How many litres they collect per rainfall
- How full their storage tank is
- How their efforts translate to real household water needs

**Jal Sanchay** solves this by acting as a
**Digital Water Wealth Passport** — recording every
rainfall event and presenting savings in meaningful,
relatable terms.

---

## ✨ Features

- 🏠 **One-time Setup** — Enter roof area, tank capacity,
  and roof type (runoff coefficient)
- 🌧️ **Rainfall Logging** — Log rainfall in mm after
  each rain event
- 💧 **Auto Calculation** — Instantly calculates litres
  harvested using the formula:
  
  Litres = Roof Area (sq ft) × Rainfall (mm)
          × 0.0929 × Runoff Coefficient

  - 🪣 **Tank Fill Animation** — Visual animated water
  level showing your tank fill percentage
- 🌍 **Impact Score** — Shows conservation impact as
  household water days saved
  (based on BIS standard of 135 L/person/day)
- 📊 **Monthly Report** — Complete date-wise log of
  all harvest entries
- 💾 **Offline First** — All data stored locally using
  Room Database, no internet needed

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Kotlin | Primary programming language |
| XML + ViewBinding | UI design and safe view access |
| MVVM Architecture | Separation of UI and data logic |
| LiveData | Reactive UI updates |
| Room Database | Local offline data storage |
| Android Animation | Tank fill water level animation |
| Material Design | UI components and theming |
| Android Studio | IDE |

---

## 🧮 Formula Used:

Litres Harvested = Roof Area (sq ft)
× Rainfall (mm)
× 0.0929        ← converts sq ft to sq metres
× Runoff Coefficient


**Impact Score:** 
Household Water Days = Total Litres ÷ 135

*(135 L/person/day — Bureau of Indian Standards)*

---

# 📂 Project Structure

```text
app/src/main/java/com/example/jalsanchaytracker/

├── MainActivity.kt
├── SetupActivity.kt
├── WaterWealthViewModel.kt
├── DashboardFragment.kt
├── LogFragment.kt
├── ReportFragment.kt
└── TipsFragment.kt
```


---

## 🚀 How to Run

1. Clone the repository
```bash
   git clone https://github.com/YOUR_USERNAME/JalSanchay.git
```
2. Open in **Android Studio**
3. Let Gradle sync complete
4. Run on emulator or physical device
   (minimum SDK: Android 7.0 / API 24)

---

## 🌱 Future Enhancements

- 🌦️ Weather API integration for automatic rainfall data
- ☁️ Firebase cloud sync across devices
- 🤖 AI-powered harvest predictions using Generative AI
- 📈 Yearly harvest trend charts
- 🗣️ Multi-language support (Hindi + regional languages)
- 🏛️ Government rainwater scheme integration

---

## 👨‍💻 Developer

**Aditya Kumar Tiwary**
- USN: 1MV22EC003
- Department of ECE, Sir MVIT Bengaluru
- Internship at MindMatrix.io CL Infotech Pvt Ltd

---

## 📄 License

This project was developed as part of an Industrial
Internship (BINT803B) at Sir M. Visvesvaraya Institute
of Technology, Bengaluru — 2025-2026.
