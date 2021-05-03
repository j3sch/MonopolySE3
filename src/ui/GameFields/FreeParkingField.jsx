import Image from 'next/image'

export const FreeParkingField = (id, borderColor) => {
  return (
    <div key={id} className={borderColor + ' max-h-64  flex col-span-2'}>
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
