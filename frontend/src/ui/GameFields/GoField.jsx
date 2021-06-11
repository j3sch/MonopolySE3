import Image from 'next/image';
import PropTypes from 'prop-types';

export const GoField = (props) => {
	const { borderColor } = props;

	return (
		<div className={`${borderColor} max-h-64 flex col-span-2`}>
			<Image
				alt="Go field"
				src="/images/go.png"
				height={100}
				width={600}
				objectFit="cover"
				priority
			/>
		</div>
	);
};

GoField.propTypes = {
	borderColor: PropTypes.string,
};
