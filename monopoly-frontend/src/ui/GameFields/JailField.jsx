import Image from 'next/image';
import PropTypes from 'prop-types';

export const JailField = (props) => {
	const { borderColor } = props;

	return (
		<div
			className={`${borderColor} max-h-64 col-span-2 grid bg-[#CEE6CE] items-end text-2xl lg:text-3xl text-center`}
		>
			Just Visiting
			<div className="bg-[#F7941D]  xl:w-52 2xl:w-60 border-t-4 border-r-4 border-black flex h-28 items-center space-x-4 justify-center text-xl md:text-2xl xl:text-4xl font-semibold">
				<p className="text-center">In</p>
				<div>
					<Image
						alt="Jail field"
						src="/images/jail.png"
						height={60}
						width={65}
						priority
					/>
				</div>
				<p className="text-center">Jail</p>
			</div>
		</div>
	);
};

JailField.propTypes = {
	borderColor: PropTypes.string,
};
