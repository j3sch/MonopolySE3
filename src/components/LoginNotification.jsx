import React, { useEffect } from 'react';
import PropTypes from 'prop-types';
import { useRouter } from 'next/router';
import { GameBoard } from './GameBoard';

/* eslint-disable */
const LoginNotification = ({
	message,
	closeNotification,
	backgroundColor,
}) => {
	const router = useRouter();
	useEffect(() => {
		{
			backgroundColor === 'bg-green-500'
				? setTimeout(() => {
						closeNotification();
						router.push('/gamePage');
				  }, 500)
				: setTimeout(() => {
						closeNotification();
				  }, 3000);
		}
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
