export const Left = (props) => {
  return (
    <div key={props.id} className={props.borderColor + ' h-full flex col-span-2'}>
      <div className='w-3/4 bg-green-200 border-black border-1 grid justify-items-center'>
        <p className='text-xl font-bold'>{props.title}</p>
        <p className='text-xl font-semibold'>{props.price}</p>
      </div>
      <div className={`${props.color} w-1/4 border-black border-1`}></div>
    </div>
  )
}
