import Image from 'next/image'

export const FreeParkingField = (props) => {
  return (
    <div
      key={props.id}
      className={props.borderColor + ' max-h-64  flex col-span-2'}
    >
      <Image
        alt='Picture of the free parking field'
        src='/images/freeParking.png'
        height={100}
        width={600}
        objectFit='cover'
        priority
      />
    </div>
  )
}
