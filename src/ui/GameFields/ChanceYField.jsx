import Image from 'next/image';
import PropTypes from 'prop-types';

export const ChanceYField = (props) => {
	const { borderColor } = props;

	return (
		<div className={`${borderColor} h-full flex col-span-2`}>
			<Image
				alt="Chance field"
				src="/images/chance.png"
				height={100}
				width={600}
				objectFit="cover"
				priority
			/>
		</div>
	);
};

ChanceYField.propTypes = {
	borderColor: PropTypes.string,
};
