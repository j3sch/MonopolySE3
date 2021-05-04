import React from 'react';
import PropTypes from 'prop-types';

export const Left = (props) => {
	const { id, borderColor, title, price, color } = props;

	return (
		<div key={id} className={`${borderColor} h-full flex col-span-2`}>
			<div className="w-3/4 bg-green-200 border-black border-1 grid justify-items-center">
				<p className="text-xl font-bold">{title}</p>
				<p className="text-xl font-semibold">{price}</p>
			</div>
			<div className={`${color} w-1/4 border-black border-1`} />
		</div>
	);
};

Left.propTypes = {
	id: PropTypes.number,
	borderColor: PropTypes.string,
	title: PropTypes.string,
	price: PropTypes.string,
	color: PropTypes.string,
};
