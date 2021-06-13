import React from 'react';
import PropTypes from 'prop-types';
import { PlayerContext } from '~/utils/PlayerContext';
import { useContext } from 'react';

export const Bottom = (props) => {
	const { id, borderColor, title, price, color } = props;
	const { boughtEstate } = useContext(PlayerContext);

	return (
		<div className={borderColor}>
			<div className="h-3/4 bg-green-200 grid justify-items-center">
				<p className="text-xl font-bold">{title}</p>
				<p className="text-xl font-semibold">{price}</p>
			</div>
			<div className={`h-1/4 ${color} flex justify-center items-center`}>
				{boughtEstate.map(
					(field) =>
						field.fieldPosition === id && (
							<div
								className={`bg-[${field.estateColor}] h-1/2 w-1/2 border border-black`}
							/>
						),
				)}
			</div>
		</div>
	);
};

Bottom.propTypes = {
	id: PropTypes.number,
	borderColor: PropTypes.string,
	title: PropTypes.string,
	price: PropTypes.string,
	color: PropTypes.string,
};