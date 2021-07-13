import PropTypes from 'prop-types';

export const GoField = (props) => {
	const { borderColor } = props;

	return (
		<div
			className={`${borderColor} max-h-64 col-span-2 grid bg-[#CEE6CE] items-center`}
		>
			<p className="text-center">COLLECT 2$ SALARY AS YOU PASS</p>
			<p className="text-center text-2xl xl:text-4xl 2xl:text-6xl font-semibold">
				GO
			</p>
			<svg
				width="50"
				height="50"
				className="fill-current text-red-600 justify-self-center"
				viewBox="0 0 20 20"
			>
				<path
					fillRule="evenodd"
					d="M10.293 3.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-4.293-4.293a1 1 0 010-1.414z"
					clipRule="evenodd"
				/>
			</svg>
		</div>
	);
};

GoField.propTypes = {
	borderColor: PropTypes.string,
};
