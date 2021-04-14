export function GameFieldLeft() {
  return (
    <div className='border-black border-2 h-full flex'>
      <div className='w-3/4 bg-gray-200 dark:bg-green-200 border-black border-1'></div>
      <div className='w-1/4 bg-gray-200 dark:bg-red-200 border-black border-1'></div>
    </div>
  )
}

export function GameFieldTop() {
  return (
    <div className='border-black border-2'>
      <div className='h-3/4 bg-gray-200 dark:bg-green-200 border-black border-1'></div>
      <div className='h-1/4 bg-gray-200 dark:bg-red-200 border-black border-1'></div>
    </div>
  )
}

export function GameFieldRight() {
  return (
    <div className='border-black border-2 h-full flex'>
      <div className='w-1/4 bg-gray-200 dark:bg-red-200 border-black border-1'></div>
      <div className='w-3/4 bg-gray-200 dark:bg-green-200 border-black border-1'></div>
    </div>
  )
}

export function GameFieldBottom() {
  return (
    <div className='border-black border-2'>
      <div className='h-1/4 bg-gray-200 dark:bg-red-200 border-black border-1'></div>
      <div className='h-3/4 bg-gray-200 dark:bg-green-200 border-black border-1'></div>
    </div>
  )
}
