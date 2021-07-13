import Image from 'next/image';
import PropTypes from 'prop-types';

export const GoToJailField = (props) => {
	const { borderColor } = props;

	return (
		<div
			className={`${borderColor} max-h-64 col-span-2 text-2xl xl:text-4xl grid bg-[#CEE6CE] items-center font-semibold`}
		>
			<p className="text-center">Go to</p>
			<div className="flex justify-self-center">
				<Image
					alt="Jail field"
					src="/images/referee.png"
					height={60}
					width={65}
					fixed
					priority
				/>
			</div>
			<p className="text-center">Jail</p>
		</div>
	);
};

GoToJailField.propTypes = {
	borderColor: PropTypes.string,
};
