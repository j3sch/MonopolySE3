import Image from 'next/image';
import PropTypes from 'prop-types';

export const ChanceXField = (props) => {
	const { borderColor } = props;

	return (
		<div className={`${borderColor} h-full flex`}>
			<Image
				alt="Picture of the chance field"
				src="/images/chance.png"
				height={100}
				width={600}
				objectFit="cover"
				priority
			/>
		</div>
	);
};

ChanceXField.propTypes = {
	borderColor: PropTypes.string,
};
