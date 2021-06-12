import Image from 'next/image';
import PropTypes from 'prop-types';

export const JailField = (props) => {
	const { borderColor } = props;

	return (
		<div className={`${borderColor} max-h-64 flex col-span-2`}>
			<Image
				alt="Jail field"
				src="/images/jail.png"
				height={100}
				width={600}
				objectFit="cover"
				priority
			/>
		</div>
	);
};

JailField.propTypes = {
	borderColor: PropTypes.string,
};
