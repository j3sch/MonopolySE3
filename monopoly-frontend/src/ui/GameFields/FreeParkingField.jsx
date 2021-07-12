import Image from 'next/image';
import PropTypes from 'prop-types';
import { useContext } from 'react';
import { PlayerContext } from '~/utils/PlayerContext';

export const FreeParkingField = (props) => {
	const { borderColor } = props;
	const { freeParkingCredit } = useContext(PlayerContext) || {};

	return (
		<div
			className={`${borderColor} max-h-64 col-span-2 text-2xl lg:text-4xl grid bg-[#CEE6CE] items-center font-semibold`}
		>
			<p className="text-center">Free Parking</p>
			<Image src="/car.svg" height={40} width={40} />
			<div className="bg-white bg-opacity-50 rounded-md p-1 h-14 w-20 text-center justify-self-center">
				{freeParkingCredit}
			</div>
		</div>
	);
};

FreeParkingField.propTypes = {
	borderColor: PropTypes.string,
};
