import Image from 'next/image'

export const JailField = (props) => {
  return (
    <div
      key={props.id}
      className={props.borderColor + ' max-h-64 flex col-span-2'}
    >
      <Image
        alt='Picture of the jail field'
        src='/images/jail.png'
        height={100}
        width={600}
        objectFit='cover'
        priority
      />
    </div>
  )
}
