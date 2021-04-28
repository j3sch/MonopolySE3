module.exports = {
  mode: 'jit',
  purge: ['./src/**/*.{js,ts,jsx,tsx}'],
  darkMode: 'media',
  theme: {
    ripple: (theme) => ({
      colors: theme('colors'),
      darken: 0.1,
    }),
    extend: {},
  },
  plugins: [],
}
