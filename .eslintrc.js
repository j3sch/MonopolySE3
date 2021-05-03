module.exports = {
	env: {
		browser: true,
		commonjs: true,
		es2021: true,
	},
	extends: [
		'plugin:react/recommended',
		'airbnb',
		'plugin:prettier/recommended',
	],
	parserOptions: {
		ecmaFeatures: {
			jsx: true,
		},
		ecmaVersion: 12,
	},
	plugins: ['react'],
	rules: {
		'jsx-a11y/anchor-is-valid': 'off',
	},
};
