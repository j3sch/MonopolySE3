import Image from 'next/image';

export const ChanceYField = (props) => {
	const { id, borderColor } = props;

	return (
		<div key={id} className={`${borderColor} h-full flex col-span-2`}>
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
