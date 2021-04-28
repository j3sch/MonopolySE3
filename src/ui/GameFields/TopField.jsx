export const Top = (props) => {
  return (
    <div key={props.id} className={props.borderColor}>
      <div className='h-3/4 bg-green-200 border-black border-1 grid justify-items-center'>
        <p className='text-xl font-bold'>{props.title}</p>
        <p className='text-xl font-semibold'>{props.price}</p>
      </div>
      <div className={`h-1/4 ${props.color} border-black border-1`}></div>
    </div>
  )
}
