import React, { useEffect } from 'react';
import PropTypes from 'prop-types';

const LoginNotification = ({ message, closeNotification, backgroundColor }) => {
	useEffect(() => {
		setTimeout(() => {
			closeNotification();
		}, 4000);
	});

	return (
		<div className={` ${backgroundColor} rounded bg-opacity-70 flex`}>
			<p className="mx-auto py-2 truncate font-semibold text-lg">{message}</p>
		</div>
	);
};

LoginNotification.propTypes = {
	message: PropTypes.string,
	closeNotification: PropTypes.func,
	backgroundColor: PropTypes.string,
};

export default LoginNotification;
