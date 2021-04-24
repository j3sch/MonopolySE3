export function GameFieldLeft() {
  return (
    <div className='border-black border-2 h-full flex col-span-2'>
      <div className='w-3/4 bg-green-200 border-black border-1 grid justify-items-center'>
        <p className='text-xl font-bold'>Name of the field</p>
        <p className='text-xl font-semibold'>M$000</p>
      </div>
      <div className='w-1/4  bg-red-200 border-black border-1'></div>
    </div>
  )
}

export function GameFieldTop(props) {
  return (
    <div className='border-black border-2'>
      <div className='h-3/4 bg-green-200 border-black border-1 grid justify-items-center'>
        <p className='text-xl font-bold'>{props.name}</p>
        <p className='text-xl font-semibold'>{props.price}</p>
      </div>
      <div className='h-1/4 bg-red-200 border-black border-1'></div>
    </div>
  )
}

export function GameFieldRight() {
  return (
    <div className='border-black border-2 h-full flex col-span-2'>
      <div className='w-1/4  bg-red-200 border-black border-1'></div>
      <div className='w-3/4 bg-green-200 border-black border-1 grid justify-items-center'>
        <p className='text-xl font-bold'>Name of the field</p>
        <p className='text-xl font-semibold'>M$000</p>
      </div>
    </div>
  )
}

export function GameFieldBottom() {
  return (
    <div className='border-black border-2'>
      <div className='h-1/4 bg-red-200 border-black border-1'></div>
      <div className='h-3/4 bg-green-200 border-black border-1 grid justify-items-center'>
        <p className='text-xl font-bold'>Name of the field</p>
        <p className='text-xl font-semibold'>M$000</p>
      </div>
    </div>
  )
}
