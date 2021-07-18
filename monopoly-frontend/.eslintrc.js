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
		'react/react-in-jsx-scope': 'off', // not required in Next.js
		'jsx-a11y/anchor-is-valid': 'off', // Link instead href in Next.js
		'react/jsx-props-no-spreading': 'off',
		'import/prefer-default-export': 'off',
		'import/no-unresolved': 'off',
		'import/extensions': 'off',
		'react/require-default-props': 'off', // gamefield data is static
		'jsx-a11y/label-has-associated-control': [
			'error',
			{
				required: {
					some: ['nesting', 'id'],
				},
			},
		],
		'jsx-a11y/label-has-for': [
			'error',
			{
				required: {
					some: ['nesting', 'id'],
				},
			},
		],
	},
};
