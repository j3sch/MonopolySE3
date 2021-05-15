import Image from 'next/image';

export const ChanceXField = (props) => {
	const { id, borderColor } = props;

	return (
		<div key={id} className={`${borderColor} h-full flex`}>
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
