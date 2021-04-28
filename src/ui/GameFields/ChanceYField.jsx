import Image from 'next/image'

export const ChanceYField = (props) => {
  return (
    <div
      key={props.id}
      className={props.borderColor + ' h-full flex col-span-2'}
    >
      <Image
        alt='Picture of the chance field'
        src='/images/chance.png'
        height={100}
        width={600}
        objectFit='cover'
        priority
      />
    </div>
  )
}
