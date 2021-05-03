import Image from 'next/image'

export const JailField = (id, borderColor) => {
  return (
    <div key={id} className={borderColor + ' max-h-64 flex col-span-2'}>
      <Image
        alt='Jail field'
        src='/images/jail.png'
        height={100}
        width={600}
        objectFit='cover'
        priority
      />
    </div>
  )
}
