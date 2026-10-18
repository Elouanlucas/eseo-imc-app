# 📱 Android ESEO - BMI & Health Tracking Application

> 🎓 **Academic Project (ESEO)** — Android mobile application developed in a pair (Baptiste Joyez & Elouan Lucas) designed to calculate, interpret, and track Body Mass Index (BMI) while featuring a user management system and personalized coaching advice.

---

## 🚀 Key Features

### 1. Activity 1: Login & Home
- **Personalization**: Entry and storage of the user's first name via `SharedPreferences` (dynamic welcome message: *"Welcome back..."*).
- **UI Security**: Dynamic validation button (enabled only when the first name is entered).
- **Adaptability**: Full support for portrait and landscape modes (reorganization of graphic components).

### 2. Activity 2: Intelligent Calculation Module
- **Dynamic Input**: Weight and height entry with unit selection (meters / centimeters).
- **Contextual Calculation**: Automatic age deduction from the date of birth to adjust BMI interpretation according to regulatory age brackets (18-35 years, 35-65 years, 65+ years).
- **Visual Feedback**: Dynamic colored gauge positioning the BMI needle in real-time.
- **Associated Features**: Result sharing via email (implicit intent), direct calendar access, and a reset button (RAZ) secured by a confirmation dialog (AlertDialog).

### 3. Activity 3 & Advanced Features (Custom Section)
- **Database & Accounts**: Complete management of a user profile and persistent background data storage.
- **Calculation History**: Chronological tracking of past BMI calculation evolutions.
- **Health & Coaching Module**: 
  - Personalized advice for weight **loss** or weight **gain**.
  - Home workout and physical exercise programs.

---

## 🛠️ Tech Stack

- **Language**: Java / Kotlin (Native Android)
- **Graphical Interface**: ConstraintLayout, XML (responsive portrait/landscape layouts)
- **Storage**: SQLite (Local database) & `SharedPreferences`
- **Collaboration Tools**: Git & GitHub

---

## 📁 Project Architecture

```text
app/src/main/
├── java/com/eseo/imc/
│   ├── MainActivity.java         # Activity 1: Home & Login
│   ├── CalculIMCActivity.java    # Activity 2: Calculations & Gauge
│   ├── HistoriqueActivity.java   # Activity 3: Tracking & DB
│   └── DatabaseHelper.java       # SQLite database management
├── res/
│   ├── layout/                   # XML Layouts (Portrait & Landscape modes)
│   ├── values/                   # strings.xml, colors.xml, styles.xml
│   └── drawable/                 # Graphic resources and animations
└── AndroidManifest.xml
```

## 👥 Authors

- **Elouan Lucas**
- **Baptiste Joyez**

---

## 📜 Licence

This project was carried out as part of an academic curriculum at ESEO. It is made available for educational purposes.
