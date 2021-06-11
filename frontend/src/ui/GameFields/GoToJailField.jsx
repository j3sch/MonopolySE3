import Image from 'next/image';
import PropTypes from 'prop-types';

export const GoToJailField = (props) => {
	const { borderColor } = props;

	return (
		<div className={`${borderColor} max-h-64  flex col-span-2`}>
			<Image
				alt="Go to jail field"
				src="/images/goToJail.png"
				height={100}
				width={600}
				objectFit="cover"
				priority
			/>
		</div>
	);
};

GoToJailField.propTypes = {
	borderColor: PropTypes.string,
};
