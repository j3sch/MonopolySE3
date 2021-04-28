import Image from 'next/image'

export const ChanceXField = (props) => {
  return (
    <div key={props.id} className={props.borderColor + ' h-full flex'}>
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
