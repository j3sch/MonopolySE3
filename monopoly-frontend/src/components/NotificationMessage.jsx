import React, { useEffect } from 'react';
import PropTypes from 'prop-types';

const NotificationMessage = ({ message, closeNotification }) => {
	useEffect(() => {
		const notificationTime = setTimeout(() => {
			closeNotification();
		}, 6000);
		return () => {
			clearTimeout(notificationTime);
		};
	}, []);

	return (
		<div className="px-12 h-20 bg-gray-900 dark:bg-gray-500 rounded-xl flex items-center">
			<p className="truncate font-semibold text-xl text-white text-center">
				{message}
			</p>
		</div>
	);
};

export default NotificationMessage;

NotificationMessage.propTypes = {
	message: PropTypes.string,
	closeNotification: PropTypes.func,
};
