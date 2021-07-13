import React, { useEffect } from 'react';
import PropTypes from 'prop-types';

const LoginNotification = ({ message, closeLoginNotification, backgroundColor }) => {
	useEffect(() => {
		let loginNotificationTime = setTimeout(() => {
			closeLoginNotification();
		}, 4000);
		return () => {
			clearTimeout(loginNotificationTime);
		};
	}, []);

	return (
		<div className={` ${backgroundColor} rounded bg-opacity-70 flex`}>
			<p className="mx-auto py-2 truncate font-semibold text-lg">{message}</p>
		</div>
	);
};

LoginNotification.propTypes = {
	message: PropTypes.string,
	closeLoginNotification: PropTypes.func,
	backgroundColor: PropTypes.string,
};

export default LoginNotification;
