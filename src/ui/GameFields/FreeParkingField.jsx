import Image from 'next/image';
import PropTypes from 'prop-types';

export const FreeParkingField = (props) => {
	const { borderColor } = props;

	return (
		<div className={`${borderColor} max-h-64 flex col-span-2`}>
			<Image
				alt="Free parking field"
				src="/images/freeParking.png"
				height={100}
				width={600}
				objectFit="cover"
				priority
			/>
		</div>
	);
};

FreeParkingField.propTypes = {
	borderColor: PropTypes.string,
};
