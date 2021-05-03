export const Right = (props) => {
  const { id, borderColor, title, price, color } = props

  return (
    <div key={id} className={borderColor + ' h-full flex col-span-2'}>
      <div className={`${color} w-1/4 border-black border-1`}></div>
      <div className='w-3/4 bg-green-200 border-black border-1 grid justify-items-center'>
        <p className='text-xl font-bold'>{title}</p>
        <p className='text-xl font-semibold'>{price}</p>
      </div>
    </div>
  )
}
