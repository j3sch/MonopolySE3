import Image from 'next/image';

export const GoField = (props) => {
	const { id, borderColor } = props;

	return (
		<div key={id} className={`${borderColor} max-h-64 flex col-span-2`}>
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
