import PropTypes from 'prop-types';

export const ChanceXField = (props) => {
	const { borderColor } = props;

	return (
		<div
			className={`${borderColor} max-h-64 text-xl lg:text-2xl 2xl:text-4xl grid bg-[#CEE6CE] items-center font-semibold`}
		>
			<p className="text-center">Chance</p>
			<svg
				width="50"
				height="50"
				className="fill-current text-pink-600 justify-self-center"
				viewBox="0 0 24 24"
			>
				<path d="M14.601 21.5c0 1.38-1.116 2.5-2.499 2.5-1.378 0-2.499-1.12-2.499-2.5s1.121-2.5 2.499-2.5c1.383 0 2.499 1.119 2.499 2.5zm-2.42-21.5c-4.029 0-7.06 2.693-7.06 8h3.955c0-2.304.906-4.189 3.024-4.189 1.247 0 2.57.828 2.684 2.411.123 1.666-.767 2.511-1.892 3.582-2.924 2.78-2.816 4.049-2.816 7.196h3.943c0-1.452-.157-2.508 1.838-4.659 1.331-1.436 2.986-3.222 3.021-5.943.047-3.963-2.751-6.398-6.697-6.398z" />
			</svg>
		</div>
	);
};

ChanceXField.propTypes = {
	borderColor: PropTypes.string,
};
