import Image from 'next/image'

export const GoToJailField = (id, borderColor) => {
  return (
    <div key={id} className={borderColor + ' max-h-64  flex col-span-2'}>
      <Image
        alt='Picture of the go to jail field'
        src='/images/goToJail.png'
        height={100}
        width={600}
        objectFit='cover'
        priority
      />
    </div>
  )
}
