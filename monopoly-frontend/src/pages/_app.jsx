import '../../styles/globals.css';
import Head from 'next/head';

/* eslint-disable react/prop-types */
function MyApp({ Component, pageProps }) {
	return (
		<div>
			<Head>
				<link rel="shortcut icon" href="/favicon.ico" />
			</Head>
			<Component {...pageProps} />
		</div>
	);
}

export default MyApp;
