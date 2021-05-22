import React from 'react';
import PropTypes from 'prop-types';

export const Right = (props) => {
	const { borderColor, title, price, color } = props;

	return (
		<div className={`${borderColor} h-full flex col-span-2`}>
			<div className={`${color} w-1/4`} />
			<div className="w-3/4 bg-green-200 grid justify-items-center">
				<p className="text-xl font-bold">{title}</p>
				<p className="text-xl font-semibold">{price}</p>
			</div>
		</div>
	);
};

Right.propTypes = {
	borderColor: PropTypes.string,
	title: PropTypes.string,
	price: PropTypes.string,
	color: PropTypes.string,
};
