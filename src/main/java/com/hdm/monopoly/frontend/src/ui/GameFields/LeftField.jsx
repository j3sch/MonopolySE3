import React from 'react';
import PropTypes from 'prop-types';
import { PlayerContext } from '~/utils/PlayerContext';
import { useContext } from 'react';

export const Left = (props) => {
	const { id, borderColor, title, price, color } = props;
	const { fieldPosition, estateColor } = useContext(PlayerContext);

	return (
		<div className={`${borderColor} h-full flex col-span-2`}>
			<div className="w-3/4 bg-green-200  grid justify-items-center">
				<p className="text-xl font-bold">{title}</p>
				<p className="text-xl font-semibold">{price}</p>
			</div>
			<div className={`${color} w-1/4 flex justify-center items-center`}>
				{id === fieldPosition && (
					<div
						className={`bg-[${estateColor}] h-1/2 w-1/2 border border-black`}
					/>
				)}
			</div>
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
