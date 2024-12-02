/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,ts,js}"],
  theme: {
    extend: {
      colors: {
        primary: '#ffffff', 
      }
    },
  },
  plugins: [
    require('daisyui')
  ],
}

