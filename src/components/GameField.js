import Image from 'next/image'

export function GameField(props) {
  let borderColor = 'border-black border-2'

  if (props.player1) {
    borderColor = 'border-red-500 border-4'
  }
  if (props.player2) {
    borderColor = 'border-blue-500 border-4'
  }
  if (props.player3) {
    borderColor = 'border-green-500 border-4'
  }
  if (props.player4) {
    borderColor = 'border-yellow-500 border-4'
  }

  switch (props.condition) {
    case 'top':
      return (
        <div className={borderColor} key={props.id}>
          <div className='h-3/4 bg-green-200 border-black border-1 grid justify-items-center'>
            <p className='text-xl font-bold'>{props.title}</p>
            <p className='text-xl font-semibold'>{props.price}</p>
          </div>
          <div className={`h-1/4 ${props.color} border-black border-1`}></div>
        </div>
      )
    case 'bottom':
      return (
        <div className={borderColor} key={props.id}>
          <div className={`${props.color} h-1/4 border-black border-1`}></div>
          <div className='h-3/4 bg-green-200 border-black border-1 grid justify-items-center'>
            <p className='text-xl font-bold'>{props.title}</p>
            <p className='text-xl font-semibold'>{props.price}</p>
          </div>
        </div>
      )
    case 'left':
      return (
        <div className={borderColor + ' h-full flex col-span-2'} key={props.id}>
          <div className='w-3/4 bg-green-200 border-black border-1 grid justify-items-center'>
            <p className='text-xl font-bold'>{props.title}</p>
            <p className='text-xl font-semibold'>{props.price}</p>
          </div>
          <div className={`${props.color} w-1/4 border-black border-1`}></div>
        </div>
      )
    case 'right':
      return (
        <div className={borderColor + ' h-full flex col-span-2'} key={props.id}>
          <div className={`${props.color} w-1/4 border-black border-1`}></div>
          <div className='w-3/4 bg-green-200 border-black border-1 grid justify-items-center'>
            <p className='text-xl font-bold'>{props.title}</p>
            <p className='text-xl font-semibold'>{props.price}</p>
          </div>
        </div>
      )
    case 'go':
      return (
        <div
          className={borderColor + ' max-h-64 flex col-span-2'}
          key={props.id}
        >
          <Image
            alt='Picture of the go field'
            src='/images/go.png'
            height={100}
            width={600}
            objectFit='cover'
            priority
          />
        </div>
      )
    case 'jail':
      return (
        <div
          className={borderColor + ' max-h-64 flex col-span-2'}
          key={props.id}
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
    case 'free parking':
      return (
        <div
          className={borderColor + ' max-h-64  flex col-span-2'}
          key={props.id}
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
    case 'go to jail':
      return (
        <div
          className={borderColor + ' max-h-64  flex col-span-2'}
          key={props.id}
        >
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
    case 'chance-x':
      return (
        <div className={borderColor + ' h-full flex bg-red-600'} key={props.id}>
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
    case 'chance-y':
      return (
        <div
          className={borderColor + ' h-full flex col-span-2 bg-red-600'}
          key={props.id}
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
}
