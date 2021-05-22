import React from 'react';
import PropTypes from 'prop-types';

export const Bottom = (props) => {
	const { borderColor, title, price, color } = props;

	return (
		<div className={borderColor}>
			<div className="h-3/4 bg-green-200 grid justify-items-center">
				<p className="text-xl font-bold">{title}</p>
				<p className="text-xl font-semibold">{price}</p>
			</div>
			<div className={`h-1/4 ${color}`} />
		</div>
	);
};

Bottom.propTypes = {
	borderColor: PropTypes.string,
	title: PropTypes.string,
	price: PropTypes.string,
	color: PropTypes.string,
};
