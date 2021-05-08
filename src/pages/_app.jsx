import '../../styles/globals.css';

/* eslint-disable react/prop-types */
function MyApp({ Component, pageProps }) {
	return (
		<div>
			<Component {...pageProps} />;
		</div>
	);
}

export default MyApp;
