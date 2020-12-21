import React from "react";

function App() {
  return (
    <div className='flex flex-col h-full items-center justify-center text-white bg-gradient-to-br from-gray-600 via-teal-700 to-gray-800'>
      <div className='w-1/3'>
        <h1 className='text-xl text-center font-extralight mb-6'>Pure Blog</h1>
        <div className='border-teal p-8 border-top-12 bg-gray-600 mb-6 rounded-lg shadow-lg'>
          <div className='mb-4'>
            <label className='font-bold text-white block m-2'>Username</label>
            <input
              type='text'
              className='block appearance-none w-full bg-white border border-grey-light hover:border-gray-500 px-2 py-2 rounded shadow'
              placeholder='Your Username'
            />
          </div>
          <div className='mb-4'>
            <label className='font-bold text-white block m-2'>Password</label>
            <input
              type='text'
              className='block appearance-none w-full bg-white border border-grey-light hover:border-gray-500 px-2 py-2 rounded shadow'
              placeholder='Your Password'
            />
          </div>
          <div className='flex items-center justify-between'>
            <button className='border-transparent bg-teal-dark hover:bg-teal text-white font-bold py-2 px-4 rounded'>
              LOGIN
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
