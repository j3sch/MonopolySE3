import React, { useEffect } from 'react';
import PropTypes from 'prop-types';

const NotificationMessage = ({ message, closeNotification }) => {
	useEffect(() => {
		{
			setTimeout(() => {
				closeNotification();
			}, 6000);
		}
	});

	return (
		<div
			className={`p-20 bg-[#111827] rounded-xl absolute left-[50%] top-[50%] transform translate-y-[-50%] translate-x-[-50%]`}
		>
			<p className="truncate font-semibold text-lg text-white pt-3 text-center">
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
