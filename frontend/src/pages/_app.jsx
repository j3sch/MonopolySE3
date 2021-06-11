import '../../styles/globals.css';
import PropTypes from 'prop-types';
import { GameField } from '~/components/GameFields';

function MyApp({ Component, pageProps }) {
	return <Component {...pageProps} />;
}

export default MyApp;

MyApp.propTypes = {
	Component: PropTypes.func,
	pageProps: PropTypes.object,
};
