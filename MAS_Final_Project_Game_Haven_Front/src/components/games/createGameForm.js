import React, { useState} from 'react';
import { createGame } from '../../services/gameService';

const CreateGameForm = ({ onCreateSuccess }) => {
  const [formData, setFormData] = useState({
    name: '',
    description: '',
    price: '',
    image: '',
    quantity: '',
    releaseDate: '',
    gameType: 'BOARD',
    licenseKeys: '',
  });

  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: type === 'checkbox' ? (checked ? 'VIDEO' : 'BOARD') : value,
    }));
  };

  const validateForm = () => {
    let isValid = true;
    const newErrors = {};

    if (!formData.name.trim()) {
      newErrors.name = 'Nah-uh, you need to add the games name';
      isValid = false;
    }

    if (!formData.price) {
      newErrors.price = 'The game cannot be for free:(';
      isValid = false;
    }

    if (!formData.quantity.trim()) {
      newErrors.quantity = 'Heyho, you should at least say how many games we have';
      isValid = false;
    }

    if (!formData.releaseDate) {
      newErrors.quantity = 'All games have to have a release date';
      isValid = false;
    }

    if (formData.gameType === 'VIDEO' && !formData.licenseKeys.trim()) {
      newErrors.licenseKeys = 'License keys are required for video games';
      isValid = false;
    }

    setErrors(newErrors);
    return isValid;
  };


const handleSubmit = async (e) => {
    e.preventDefault();

    if (validateForm()) {
      try {
        const newGame = await createGame(formData);
        onCreateSuccess(newGame);
      } catch (error) {
        console.log('Error creating game: ' + error);
      }
    } else {
      console.log('Form validation failed');
    }
  };

  return (
    <div>
    <form className="form" onSubmit={handleSubmit}>
    <h1>Create game</h1>
      <div>
        <label>Name:</label>
        <input
          type="text"
          name="name"
          value={formData.name}
          onChange={handleChange}
        />
        {errors.name && <p className="error">{errors.name}</p>}
      </div>

      <div>
          <label>Game Type:</label>
          <input
            type="checkbox"
            name="gameType"
            checked={formData.gameType === 'VIDEO'}
            onChange={handleChange}
          /> Is Video Game
        </div>

      <div>
        <label>Description:</label>
        <textarea
          name="description"
          value={formData.description}
          onChange={handleChange}
        />
        {errors.description && <p className="error">{errors.description}</p>}
      </div>

      <div>
          <label>Release date:</label>
          <input
            type="date"
            name="releaseDate"
            value={formData.releaseDate}
            onChange={handleChange}
          />
          {errors.releaseDate && <p className="error">{errors.releaseDate}</p>}
        </div>

      <div>
        <label>Price:</label>
        <input
          type="number"
          name="price"
          value={formData.price}
          
          onChange={handleChange}
        />
        {errors.price && <p className="error">{errors.price}</p>}
      </div>

      <div>
        <label>Image:</label>
        <input
          type="text"
          name="image"
          value={formData.image}
          onChange={handleChange}
        />
      </div>

      <div>
        <label>Quantity:</label>
        <input
          type="number"
          name="quantity"
          value={formData.quantity}
          onChange={handleChange}
        />
        {errors.quantity && <p className="error">{errors.quantity}</p>}
      </div>
      <button type="submit">Create Game</button>
  
      </form>
      </div>
  );
};

export default CreateGameForm;
